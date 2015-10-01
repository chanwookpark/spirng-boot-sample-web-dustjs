var templates = {};

/**
 * Rendering template...
 *
 * @param template HTML text
 * @param model Spring's ModelMap object
 * @param url URL for Template HTML file
 * @returns {String}
 */
function render(template, model, url) {
    // Compile template if needs
    if (templates[url] === undefined) {
        var compiled = dust.compile(template, url); // compile
        dust.loadSource(compiled); // load
    }

    // Java object to JavaScript object
    var jsonObject = convertToJsonObject(model);

    console.log(
        "'" + url + "' template is ready to rendering!\n" +
        "=============================================================\n"
        + template + "\n"
        + model + "\n"
        + "=============================================================");

    // Render
    // Dust is basically asynchronously then this code has potentially issue with synchronous..
    var res;
    dust.render(url, jsonObject, function (err, data) {
        if (err) {
            throw new Error(err);
        } else {
            res = data;
        }
    });
    return res;
}

// thanks to https://github.com/sdeleuze/spring-react-isomorphic/blob/master/src/main/resources/static/render.js
function convertToJsonObject(model) {
    var o = {};
    for (var k in model) {
        if (model[k] instanceof Java.type("java.lang.Iterable")) {
            o[k] = Java.from(model[k]);
        } else {
            o[k] = model[k];
        }
    }
    return o;
}