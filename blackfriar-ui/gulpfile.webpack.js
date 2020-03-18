var WebpackDevServer = require('webpack-dev-server');
var webpackConfig = require('./webpack.config');
var webpackDevConfig = require('./webpack.dev.config');
var webpack = require('webpack');
var path = require('path');

var gulp = require('gulp');
var gutil = require('gulp-util');

gulp.task('webpack', function (callback) {
   webpack(webpackConfig, function (err, stats) {
       if (err) throw new gutil.PluginError("webpack", err);
       gutil.log("[webpack]", stats.toString());
       callback();
   })
});

gulp.task('webpack-dev', function () {
   'use strict';

   const port = 3000;
   const host = "0.0.0.0";

   var compiler = webpack(webpackDevConfig);

   new WebpackDevServer(compiler, {
       inline: true,
       hot: true,
       contentBase: path.resolve(__dirname, 'dist'),
       publicPath: "/",
       proxy: [
           {
               context: [
                   '/blackfriar/api',
                   '/blackfriar-ws/',
                   '/js/blackfriar-config.js',
                   '/api/manage/swagger',
                   '/blackfriar/webjars',
                   '/configuration',
                   '/swagger-resources',
                   '/docs/swagger',
                   '/blackfriar/swagger-ui.html',
                   '/blackfriar/docs/swagger',
                   '/blackfriar/configuration',
                   '/blackfriar/swagger-resources',
                   'favicon.ico'
               ],
               target: 'http://localhost:8080',
               pathRewrite: {
                   '^/api/manage/swagger': '^/blackfriar/api/manage/swagger',
                   '^/configuration': '^/blackfriar/configuration',
                   '^/swagger-resources': '^/blackfriar/swagger-resources',
                   '^/docs/swagger': '^/blackfriar/docs/swagger',
                   '^/swagger-ui.html': '^/blackfriar/swagger-ui.html',
                   '^/js/blackfriar-config.js': '^/blackfriar/js/blackfriar-config.js',
                   '^/blackfriar-ws/': '^/blackfriar/ws',
                   '^/favicon.ico': '^/blackfriar/favicon.ico'
               },
               headers: {
                   'Access-Control-Allow-Origin': '*',
                   'Access-Control-Allow-Methods': '*'
               },
               logLevel: 'debug',
               ws: true
           }
       ]
   }).listen(port, host, function (err) {
       if (err) throw new gutil.PluginError("webpack-dev-server", err);
   })
});