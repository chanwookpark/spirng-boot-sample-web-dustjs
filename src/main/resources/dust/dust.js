function render(template, model) {
    var key = "_key"; // temp key
    var compiled = dust.compile(template, key); // compile
    dust.loadSource(compiled); // load

    // render
    var res;
    dust.render(key, model, function (err, data) {
        if (err) {
            throw new Error(err);
        } else {
            res = data;
        }
    });
    return res;
}