var templates = {};

function render(template, model, url) {
    if (templates[url] === undefined) {
        var compiled = dust.compile(template, url); // compile
        dust.loadSource(compiled); // load
    }

    if (console != undefined) {
        console.log(url + " template is ready to rendering!\n" +
            "=============================================================\n"
            + template
            + "\n=============================================================");
    }

    // Dust is basically asynchronously then this code has potentially issue with synchronous..
    var res;
    dust.render(url, /* needs convert to real JS JSON object?*/model, function (err, data) {
        if (err) {
            throw new Error(err);
        } else {
            res = data;
        }
    });
    return res;
}