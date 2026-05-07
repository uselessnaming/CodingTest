import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val word = "Muzi"
    val pages = arrayOf(
        """<html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <meta charset="utf-8">
              <meta property="og:url" content="https://careers.kakao.com/interview/list"/>
            </head>
            <body>
                <a href="https://programmers.co.kr/learn/courses/4673"></a>#!MuziMuzi!)jayg07con&&
            </body>
            </html>""".trimIndent(),
        """<html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <meta charset="utf-8">
              <meta property="og:url" content="https://www.kakaocorp.com"/>
            </head>
            <body>
            con%    muzI92apeach&2<a href="https://hashcode.co.kr/tos"></a>
            
                ^
            </body>
            </html>""".trimIndent(),
    )
    val answer = MatchingScore().solution(word, pages)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}