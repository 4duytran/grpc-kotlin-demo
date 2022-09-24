package user.client

import com.proto.user.UserInfoServerStreamGrpc
import com.proto.user.UserRequest
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

class UserClientStream(private val chanel: ManagedChannel) {
    private val stub : UserInfoServerStreamGrpc.UserInfoServerStreamBlockingStub =
        UserInfoServerStreamGrpc.newBlockingStub(chanel)

    fun getUserInfoStream(firstName: String, lastName:String) {
        stub.getUserInfoServerStream(UserRequest.newBuilder().setFirstName(firstName).setLastName(lastName).build())
            .forEachRemaining{
                println("Received message from server : ${it.firstName} ${it.lastName}")
            }

    }
}

fun main(args: Array<String>) {
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build()
    val sendRequest = UserClientStream(channel)
    if (args.size >= 2) sendRequest.getUserInfoStream(args[0] , args[1] )
    else sendRequest.getUserInfoStream("Jonh", "Don")

}