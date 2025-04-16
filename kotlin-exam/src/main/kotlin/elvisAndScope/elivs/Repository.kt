package elvisAndScope.elivs

interface Repository {

    fun findById(id: Long): Member?

    fun save(member: Member): Member
}
