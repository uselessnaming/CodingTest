package 프로그래머스

typealias P = Pair<Int,Int>
typealias P2 = Pair<List<P>,Int>

class MoveBlock {
    //아래, 위, 오른쪽, 왼쪽
    val move = arrayOf(P(1,0), P(-1,0), P(0,1), P(0,-1))
    //<아래,오른쪽>, <아래, 왼쪽>, <위,오른쪽>, <위, 왼쪽>
    val rot_move = arrayOf(P(1,1), P(1,-1) , P(-1,1), P(-1,-1))
    var boardSize = 0

    enum class NSEW{
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    fun P.minus(_p:P) : P = P(this.first - _p.first, this.second - _p.second)
    fun P.plus(_p:P) : P = P(this.first + _p.first, this.second + _p.second)



    fun solution(board: Array<IntArray>): Int {
        boardSize = board.size

        //bfs위한 queue
        //해쉬맵 자료구조 (방문용 체크), drone의 좌표 담을 Pair, 드론의 move[4] P, 드론의 로테이션 관리. 반시계,시계.
        //board넘어갔는지 체크, board의 벽에 위치했는지 check, 회전 시 회전방향에 보드 벽 있는지 체크.
        //드론의 중심에서 다른 한 쪽이 어느 방향에 있는지 알아내는 그런 함수.

        return bfs(board, listOf(P(0,0), P(0,1)) )
    }

    fun bfs(board:Array<IntArray>, start: List<P>) : Int {
        //드론이 움직인 위치를 담는 queue
        val q : ArrayList<P2> = arrayListOf()
        //드론의 방문 위치를 체크하는 해쉬맵. key는
        val visit : HashMap<String,Boolean> = hashMapOf()

        //start q에 푸쉬
        q.add(P2(start,0))
        //첫 start 위치 방문 체크.
        visit.put(makeKey(start), true)

        //BFS시작.
        while(!q.isEmpty()) {
            val (s,c) = q[0]
            q.removeAt(0)

            if(findResult(s)) return c

            //move
            for(i in 0 until 4) {
                val next = moveDrone(s, move[i])
                if(checkBoard(next,board)) continue
                if(checkBlock(next,board) || checkVisit(next,visit)) continue

                visit.put(makeKey(next),true)

                q.add(P2(next,c+1))
            }


            //rotate 시계
            for(center in 0..1) {
                val rotIdx = if(center==0) 1 else 0
                val loc = s[rotIdx].minus(s[center])
                val nsew = getDirection(loc)

                val next = rotate(s[center], s[rotIdx], nsew).sortedWith(compareBy({it.first},{it.second}))

                if(checkBoard(next,board)) continue
                if(checkVisit(next,visit)) continue
                if(checkRotBlock(s[rotIdx],board,nsew) || checkBlock(next, board)) continue

                visit.put(makeKey(next),true)
                q.add(P2(next,c+1))

            }


            //rotate 반시계
            for(center in 0..1) {
                val rotIdx = if(center==0) 1 else 0
                val loc = s[rotIdx].minus(s[center])
                val nsew = getDirection(loc)

                val next = rotate_R(s[center], s[rotIdx], nsew).sortedWith(compareBy({it.first},{it.second}))

                if(checkBoard(next,board)) continue
                if(checkVisit(next,visit)) continue
                if(checkRotBlock_R(s[rotIdx],board,nsew) || checkBlock(next, board)) continue

                visit.put(makeKey(next),true)
                q.add(P2(next,c+1))

            }
        }

        return -1
    }

    fun makeKey(_key:List<P>) : String  = "${_key[0].first},${_key[0].second}+${_key[1].first},${_key[1].second}"

    //이미 방문된 위치라면 true, 방문되지 않은 위치라면 false
    fun checkVisit(_key:List<P>, visit:HashMap<String,Boolean>) : Boolean  = visit[makeKey(_key)] ?: false

    //드론이 board를 넘어갔는지 확인, 넘어갔다면 true 그렇지 않으면 false
    fun checkBoard(drone:List<P>, board:Array<IntArray>) : Boolean {
        val (d1,d2) = drone
        return (d1.first < 0 || d1.second < 0 || d1.first >= boardSize || d1.second >= boardSize || d2.first < 0 || d2.second < 0 || d2.first >= boardSize || d2.second >= boardSize)
    }

    //드론이 board = 1 위에 위치하고 있는지 확인, 그렇다면 true 그렇지 않다면 false
    fun checkBlock(drone:List<P>, board: Array<IntArray>) : Boolean {
        val (d1,d2) = drone
        return (board[d1.first][d1.second] == 1 || board[d2.first][d2.second] == 1)
    }

    //드론을 아래, 위, 오른쪽, 왼쪽 (P(1,0), P(-1,0), P(0,1), P(0,-1)) 으로 이동시키는 함수
    fun moveDrone(drone: List<P>, dir : P) : List<P> {
        val (d1,d2) = drone
        return listOf(d1.plus(dir), d2.plus(dir))
    }

    //드론의 중심점을 기준으로 다른 부품이 어느 방향에 있는지 리턴하는 함수
    fun getDirection(loc:P) : NSEW  = if(loc == move[0]) NSEW.SOUTH
    else if(loc == move[1]) NSEW.NORTH
    else if(loc == move[2]) NSEW.EAST
    else NSEW.WEST

    //드론 회전 시계
    //dirt = direction 방위
    //<아래,오른쪽>, <아래, 왼쪽>, <위,오른쪽>, <위, 왼쪽>
    fun rotate(d_center:P, d_rot:P, dirt: NSEW) : List<P> = when(dirt){
        NSEW.NORTH -> listOf(d_center, d_rot.plus(rot_move[0]))
        NSEW.SOUTH -> listOf(d_center, d_rot.plus(rot_move[3]))
        NSEW.EAST -> listOf(d_center, d_rot.plus(rot_move[1]))
        NSEW.WEST -> listOf(d_center, d_rot.plus(rot_move[2]))
    }


    fun rotate_R(d_center:P, d_rot:P, dirt: NSEW) : List<P> = when(dirt){
        NSEW.NORTH -> listOf(d_center, d_rot.plus(rot_move[1]))
        NSEW.SOUTH -> listOf(d_center, d_rot.plus(rot_move[2]))
        NSEW.EAST -> listOf(d_center, d_rot.plus(rot_move[3]))
        NSEW.WEST -> listOf(d_center, d_rot.plus(rot_move[0]))
    }





    //dirt = direction 방위
    //드론의 회전 진행 방향에 블록이 있는지 확인, 회전 (시계방향) 방향에 블록이 있으면 true 그렇지 않으면 false
    fun checkRotBlock(drone_rot:P, board:Array<IntArray>, dirt:NSEW) : Boolean {
        //clockWise가 시계 방향일 경우
        return  when(dirt){
            NSEW.NORTH -> {
                val (idx1,idx2) = drone_rot.plus(move[2])
                if(board[idx1][idx2] == 1) true else false
            }
            NSEW.SOUTH -> {
                val (idx1,idx2) = drone_rot.plus(move[3])
                if(board[idx1][idx2] == 1) true else false
            }
            NSEW.EAST -> {
                val (idx1,idx2) = drone_rot.plus(move[0])
                if(board[idx1][idx2] == 1) true else false
            }
            NSEW.WEST -> {
                val (idx1,idx2) = drone_rot.plus(move[1])
                if(board[idx1][idx2] == 1) true else false
            }
        }
    }

    //dirt = direction 방위
    fun checkRotBlock_R(drone_rot:P, board:Array<IntArray>,dirt:NSEW) : Boolean {
        //clockWise가 반시계 방향일 경우
        return  when(dirt){
            NSEW.NORTH -> {
                val (idx1,idx2) = drone_rot.plus(move[3])
                if(board[idx1][idx2] == 1) true else false
            }
            NSEW.SOUTH -> {
                val (idx1,idx2) = drone_rot.plus(move[2])
                if(board[idx1][idx2] == 1) true else false
            }
            NSEW.EAST -> {
                val (idx1,idx2) = drone_rot.plus(move[1])
                if(board[idx1][idx2] == 1) true else false
            }
            NSEW.WEST -> {
                val (idx1,idx2) = drone_rot.plus(move[0])
                if(board[idx1][idx2] == 1) true else false
            }
        }
    }

    //drone의 한 부분이라도 보드 사이즈에 도달하면 true, 그렇지 않으면 false
    fun findResult(drone: List<P>) : Boolean {
        val (d1,d2) = drone
        return ((d1.first == boardSize-1 && d1.second == boardSize-1) || (d2.first == boardSize-1 && d2.second == boardSize-1))
    }
}