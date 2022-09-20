package user.client

import com.proto.user.UserInfoGrpc
import com.proto.user.UserRequest
import com.proto.user.UserResponse
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

class UserClient(private val chanel: ManagedChannel) {
    private val stub : UserInfoGrpc.UserInfoBlockingStub = UserInfoGrpc.newBlockingStub(chanel)

    fun getUserInfo(firstName: String, lastName:String) {
        val result: UserResponse = stub.getUserInfo(UserRequest.newBuilder().setFirstName(firstName).setLastName(lastName).build())
        println("Response from server : Hello ${result.firstName} ${result.lastName}")
    }
}

fun main(args: Array<String>) {
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build()
    val sendRequest = UserClient(channel)
    if (args.size >= 2) sendRequest.getUserInfo(args[0] , args[1] )
    else sendRequest.getUserInfo("Jonh", "Don")

}