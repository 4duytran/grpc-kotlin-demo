package user.server

import io.grpc.Server
import io.grpc.ServerBuilder

class UserServer(private val port: Int ) {

    private val server : Server = ServerBuilder
        .forPort(port)
        .addService(UserServerImpl())
        .build()

    fun start() {

        server.start()
        println("Server starting with port: $port")
        println("Listening ..... ")
        Runtime.getRuntime().addShutdownHook(Thread {
            println("*** shutting down gRPC server since JVM is shutting down")
            this@UserServer.stop()
            println("*** server shut down")
        })

        this@UserServer.blockUntilShutdown()
    }

    private fun stop(): Server = server.shutdown()

    private fun blockUntilShutdown() {
        server.awaitTermination()
    }
}

fun main() {
    val port = 50051
    val server = UserServer(port)
    server.start()
}