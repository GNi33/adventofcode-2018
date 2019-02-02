package app.factory.model

class Guard(val id: Int) : IGuard {
    val guardTimes = mutableMapOf<String, GuardTime>()
}
