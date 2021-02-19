package com.example.marvelproject.base.util

import java.math.BigInteger
import java.security.MessageDigest

 fun String.tomd5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(this.toByteArray())).toString(16).padStart(32, '0')
