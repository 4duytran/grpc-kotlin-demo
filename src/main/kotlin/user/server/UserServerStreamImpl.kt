package user.server

import com.proto.user.UserInfoServerStreamGrpc
import com.proto.user.UserRequest
import com.proto.user.UserResponse
import io.grpc.stub.StreamObserver

class UserServerStreamImpl: UserInfoServerStreamGrpc.UserInfoServerStreamImplBase() {

    override fun getUserInfoServerStream(request: UserRequest, responseObserver: StreamObserver<UserResponse>) {

        val listOfWords = listOf("Bonjour", "Hello", "Hola", "Konnichiwa")

        listOfWords.forEach {
            responseObserver.onNext(UserResponse.newBuilder()
                .setFirstName("$it  ${request.firstName}").setLastName(request.lastName)
                .build())
        }
        responseObserver.onCompleted()
    }
}