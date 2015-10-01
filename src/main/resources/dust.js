var templates = {};

function render(template, model, url) {
    // compile template if needs
    if (templates[url] === undefined) {
        var compiled = dust.compile(template, url); // compile
        dust.loadSource(compiled); // load
    }

    // to JSON object
    var jsonObject = convertToJsonObject(model);

    console.log(
        "'" + url + "' template is ready to rendering!\n" +
        "=============================================================\n"
        + template + "\n"
        + model + "\n"
        + "=============================================================");

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