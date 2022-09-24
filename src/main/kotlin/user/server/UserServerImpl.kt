package user.server

import com.proto.user.UserInfoGrpc
import com.proto.user.UserRequest
import com.proto.user.UserResponse
import io.grpc.stub.StreamObserver

class UserServerImpl: UserInfoGrpc.UserInfoImplBase() {

    override fun getUserInfo(request: UserRequest, responseObserver: StreamObserver<UserResponse>) {
        responseObserver.onNext(UserResponse.newBuilder().setFirstName(request.firstName).setLastName(request.lastName)
            .build())
        responseObserver.onCompleted()
    }

}