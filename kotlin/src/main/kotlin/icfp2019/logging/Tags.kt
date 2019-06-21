package icfp2019.logging

/*
    Various log level(s)
 */
val Any.INFO: String
    get() {
        return "INFO: "
    }

val Any.DEBUG: String
    get() {
        return "DEBUG: "
    }

val Any.TRACE: String
    get() {
        return "TRACE: "
    }
