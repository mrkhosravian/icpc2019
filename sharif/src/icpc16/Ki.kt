package icpc16

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/*
    Doesn't work for tests 10 & 11
 */


enum class Flag{
    NotMarked,
    Master,
    ConnectedToMaster
}

class Member(val followers: MutableList<Int>,
             val following: MutableList<Int> = ArrayList(),
             var state: Flag = Flag.NotMarked)

val members : HashMap<Int, Member> = HashMap()
fun main(){
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()

    // get input and followers
    for (i in 1..n){
        val tempFollowing = ArrayList<Int>()
        val followingCount = scanner.nextInt()
        for (j in 1..followingCount){
            tempFollowing.add(scanner.nextInt())
        }
        members.put(i, Member(tempFollowing))
    }

    salution()
}

fun salution(){

    // get following
    members.forEach{ member ->
        member.value.followers.forEach {
            members.get(it)?.following?.add(member.key)
        }
    }

    //mark the nodes
    members.forEach { markNodes(it.key,true) }

    //check if a master nodes follows another master node
    val masters = members.filter { it.value.state==Flag.Master }
    masters.forEach { curr ->
        curr.value.following.forEach {
            if(get(it).state == Flag.Master){
                curr.value.state = Flag.ConnectedToMaster
            }
        }
    }

    print(getByFlag(Flag.Master))
}

fun markNodes( currentId: Int, firstRun: Boolean = false,history : ArrayList<Int> = ArrayList()){
        val member = get(currentId)
        if (member.state != Flag.NotMarked) return  // already marked
        val masterParent = member.following.find { get(it).state == Flag.Master }
        if (masterParent.exists()) { // following a master node
            member.state = Flag.ConnectedToMaster
            markChildrenAsConnectedToMaster(member)
            return
        }
        val connectedToMasterNode = member.following.find { get(it).state == Flag.ConnectedToMaster }
        if (connectedToMasterNode.exists()) { // following a node that follows a master node
            member.state = Flag.ConnectedToMaster
            markChildrenAsConnectedToMaster(member)
            return
        }
        if (history.contains(currentId) && !firstRun) { // we looped
            member.state = Flag.Master
            markChildrenAsConnectedToMaster(member)
            return
        }
        if (member.following.size == 0) {  // got to a master node
            member.state = Flag.Master
            markChildrenAsConnectedToMaster(member)
            return
        }
        history.add(currentId)
        member.following.forEach {
            markNodes(it, false, history)
        }
}

fun markChildrenAsConnectedToMaster(member: Member,firstRun: Boolean = true){
    if (member.state != Flag.NotMarked && !firstRun) return
    if (!firstRun && member.state != Flag.Master) member.state = Flag.ConnectedToMaster
    member.followers.forEach { markChildrenAsConnectedToMaster(get(it),false) }
}

private fun Int?.exists(): Boolean {
    return this != null
}

fun getByFlag(flag: Flag) : Int{
    var counter = 0
    members.forEach { if (it.value.state == flag) counter++ }
    return counter
}

fun get(id: Int): Member{
    return members.get(id)!!
}