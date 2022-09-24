package user.server

import io.grpc.Server
import io.grpc.ServerBuilder

class UserServerStream(private val port: Int ) {

    private val server : Server = ServerBuilder
        .forPort(port)
        .addService(UserServerStreamImpl())
        .build()

    fun start() {

        server.start()
        println("Server starting with port: $port")
        println("Listening ..... ")
        Runtime.getRuntime().addShutdownHook(Thread {
            println("*** shutting down gRPC server since JVM is shutting down")
            this@UserServerStream.stop()
            println("*** server shut down")
        })

        this@UserServerStream.blockUntilShutdown()
    }

    private fun stop(): Server = server.shutdown()

    private fun blockUntilShutdown() {
        server.awaitTermination()
    }
}

fun main() {
    val port = 50051
    val server = UserServerStream(port)
    server.start()
}