var jsonMapper = new com.fasterxml.jackson.databind.ObjectMapper();

/**
 * Rendering template...
 *
 * @param template HTML text
 * @param model Spring's ModelMap object
 * @param url URL for Template HTML file
 * @returns {String}
 */
function render(src, model, url) {
    var compiled = dust.compile(src); // compile - 운영에서는 pre-compile 필요오~
    var template = dust.loadSource(compiled); // load

    var jsArray = convertToJsArray(model);
    var json = jsonMapper.writeValueAsString(jsArray);

    console.log(
        "##################################################\n" +
        "화면 렌더링 로그" +
        "\n1. 템플릿 파일 경로(URL) : " + url +
        "\n2. 데이터(JSON) : " + json +
        "\n3. 화면코드(HTML) : \n" + src +
        "\n4. 렌더링코드(compiled HTML): \n" + template +
        "\n##################################################"
    );

    var res;
    dust.render(template, JSON.parse(json), function (err, data) {
        if (err) {
            throw new Error(err);
        } else {
            res = data;
        }
    });
    return res;
}

// thanks to https://github.com/sdeleuze/spring-react-isomorphic/blob/master/src/main/resources/static/render.js
function convertToJsArray(javaObject) {
    var objectArray = {};
    for (var k in javaObject) {
        if (javaObject[k] instanceof Java.type("java.lang.Iterable")) {
            objectArray[k] = Java.from(javaObject[k]);
        } else if (javaObject[k] instanceof Java.type("org.springframework.validation.Errors")) {
            objectArray[k] = "";
        } else {
            objectArray[k] = javaObject[k];
        }
    }
    return objectArray;
}