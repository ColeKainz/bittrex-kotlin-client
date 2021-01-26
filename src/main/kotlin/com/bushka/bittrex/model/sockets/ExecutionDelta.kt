package com.bushka.bittrex.model.sockets

data class ExecutionDelta(
        val accountId: String,
        override val sequence: Int,
        val deltas: List<ExecutionDelta>
) : Sequenced