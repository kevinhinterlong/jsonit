import com.hinterlong.kevin.jsonit.data.JsonNode
import com.hinterlong.kevin.jsonit.parser.DefaultJsonParser
import com.hinterlong.kevin.jsonit.parser.JsonParser

import org.antlr.v4.runtime.CharStreams

import spock.lang.Specification

class DefaultJsonParserTest extends Specification {
    JsonParser parser = new DefaultJsonParser();
    static def jsonStr =
"""{
  "obj": {
    "nest": "nice",
    "arr": [
      1.0,
      null,
      {
        "ok": true,
        "an": {
          "layer": "deep"
        }
      }
    ]
  }
}"""

    def "Parse"() {
        setup:
        JsonNode node = parser.parse(CharStreams.fromString(json))


        expect:
        node.prettyPrint() == jsonStr
        node.getType() == type

        where:
        json    | type
        jsonStr | JsonNode.Type.OBJECT
    }
}
