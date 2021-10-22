package fr.polytech.dojopoker.exceptions

class HandSizeException

    (size: Int, actualSize: Int) :
    Exception("La main doit avoir une taille de " + size + " carte" + (if (actualSize > 1) "s" else "") + ", alors que la main rentrée a une taille de " + actualSize + " carte" + (if (actualSize > 1) "s" else "") + ".")