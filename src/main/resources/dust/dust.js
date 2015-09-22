var templates = {};

function render(template, model, url) {

    if (templates[url] === undefined) {
        var compiled = dust.compile(template, url); // compile
        dust.loadSource(compiled); // load
    }

    // render. dust is basically asynchronously then this code has potentially issue with synchronous..
    var res;
    dust.render(url, model, function (err, data) {
        if (err) {
            throw new Error(err);
        } else {
            res = data;
        }
    });
    return res;
}