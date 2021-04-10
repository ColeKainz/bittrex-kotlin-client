package com.bushka.bittrex.credentials

import java.math.BigInteger
import java.security.MessageDigest

/**
 * Class that hashes strings to SHA512 with no underlying secret
 */
class MacSHA512 {
    companion object {
        fun hash(payload: String): String {
            val md: MessageDigest = MessageDigest.getInstance("SHA-512")
            val messageDigest = md.digest(payload.toByteArray())

            // Convert byte array into signum representation
            val no = BigInteger(1, messageDigest)

            // Convert message digest into hex value
            var hashtext: String = no.toString(16)

            // Add preceding 0s to make it 32 bit
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }
            return hashtext
        }
    }
}