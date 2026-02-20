/** EagerSingleton 패턴 구현 */
class EagerSingleton private constructor() {

    companion object {
        val instance = EagerSingleton()
    }
}

/** LazySingleton 패턴 구현 */
class LazySingleton private constructor() {
    companion object {
        val instance: LazySingleton by lazy {
            LazySingleton()
        }
    }
}

/** Observer 패턴 구현 */
enum class Status {
    LOADING, SUCCESS, FAILED
}

interface StatusObserver {
    fun onChanged(status: Status)
}

class ObserverA(): StatusObserver {
    override fun onChanged(status: Status) {
        println("observerA : $status")
    }
}

class ObserverB(): StatusObserver {
    override fun onChanged(status: Status) {
        println("observerB : $status")
    }
}

class Subject() {
    private val observers: MutableList<StatusObserver> = mutableListOf()
    private var status: Status = Status.LOADING

    fun registerObserver(observer: StatusObserver) {
        observers.add(observer)
    }

    fun unregisterObserver(observer: StatusObserver) {
        observers.remove(observer)
    }

    fun updateStatus(status: Status) {
        this.status = status
        notifyObserver(status)
    }

    private fun notifyObserver(status: Status) {
        observers.forEach{ it.onChanged(status) }
    }
}

/** Factory 패턴 */
interface Character {
    val name: String
}

class Magician(private val skill: String): Character {
    override val name: String = "마법사"
}

class Archer(): Character {
    override val name: String = "궁수"
}

class CharacterInfo(val initPower: Int)

class CharacterFactory{
    companion object {
        fun createCharacterInfo(character: Character): CharacterInfo {
            return when(character) {
                is Magician -> CharacterInfo(100)
                is Archer -> CharacterInfo(10)
                else -> throw Exception("")
            }
        }
    }
}

fun main() {
    val observerA = ObserverA()
    val observerB = ObserverB()
    val subject = Subject()

    subject.apply{
        registerObserver(observerA)
        updateStatus(Status.FAILED)
        registerObserver(observerB)
        updateStatus(Status.SUCCESS)
        unregisterObserver(observerA)
        updateStatus(Status.LOADING)
    }
}