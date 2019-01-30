var webpack = require("webpack");

module.exports = {
    entry: __dirname+"/app/assets/js/app.js",
    output: {
        path: __dirname+"/app/js",
        filename: "app.js"
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery'
        })
    ],
}