package org.grails.commons
/**
 * @author Graeme Rocher
 * @since 1.1
 */
class DefaultGrailsCodecClassTests extends GroovyTestCase {

    protected void setUp() {
        ExpandoMetaClass.enableGlobally()
    }

    protected void tearDown() {
        ExpandoMetaClass.disableGlobally()
    }

    void testCodecWithClosures() {
        def codecClass = new DefaultGrailsCodecClass(CodecWithClosuresCodec)
        assertEquals "encoded", codecClass.encoder.encode("stuff")
        assertEquals "decoded", codecClass.decoder.decode("stuff")
    }

    void testCodecWithMethods() {
        def codecClass = new DefaultGrailsCodecClass(CodecWithMethodsCodec)
        assertEquals "encoded", codecClass.encoder.encode("stuff")
        assertEquals "decoded", codecClass.decoder.decode("stuff")
    }
}

class CodecWithClosuresCodec {
    def encode = { "encoded" }
    def decode = { "decoded" }
}

class CodecWithMethodsCodec {
    def encode(obj) { "encoded" }
    def decode(obj) { "decoded" }
}
