const webpack = require('webpack');
const path = require('path');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const CssExtractPlugin = require('mini-css-extract-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const SpeedMeasurePlugin = require('speed-measure-webpack-plugin');

const smp = new SpeedMeasurePlugin({
    granularLoadData: true
});

module.exports = smp.wrap({
    mode: 'production',
    context: path.resolve(__dirname, 'src'),
    entry: ['polyfills', './js/app.js'],
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: '[name].[hash].bundle.js',
        chunkFilename: '[name].[hash].bundle.js'
    },
    optimization: {
        minimize: false,
        splitChunks: {
            cacheGroups: {
                commons: {
                    test: /node_modules/,
                    chunks: 'initial',
                    name: 'vendor'
                }
            }
        }
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                exclude: /\.module\.css/,
                use: [
                    CssExtractPlugin.loader,
                    'css-loader'
                ]
            },
            {
                test: /\.module\.css$/,
                include: path.resolve(__dirname, 'src'),
                use: [
                    CssExtractPlugin.loader,
                    {
                        loader: 'css-loader',
                        options: {
                            modules: true,
                            localIdentName: "[local]__[hash:base64:5]",
                        }
                    }
                ]
            },
            {
                test: /\.(png|svg|jpg|gif|ttf|eot|woff|woff2)$/,
                use: {
                    loader: 'file-loader',
                    options: {
                        limit: 10000
                    }
                }
            },
            {
                test: require.resolve('jquery'),
                use: [
                    'expose-loader?jQuery',
                    'expose-loader?$'
                ]
            },
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                use: 'import-glob',
                enforce: "pre"
            },
            {
                test: /\.jsx?$/,
                use: ['thread-loader', 'imports-loader?$=jquery,jQuery=jquery']
            },
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                use: [
                    'thread-loader',
                    'babel-loader'
                ]
            },
            {
                test: /\.html?$/,
                exclude: /node_modules/,
                use: ['thread-loader', 'html-loader']
            }
        ]
    },
    resolve: {
        modules: [path.resolve(__dirname, 'node_modules'), path.resolve(__dirname, 'src')],
        alias: {
            'isteven-angular-multiselect/isteven-multi-select.js': path.resolve(__dirname, 'node_modules/isteven-angular-multiselect/isteven-multi-select.js'),
            'isteven-angular-multiselect/isteven-multi-select.css': path.resolve(__dirname, 'node_modules/isteven-angular-multiselect/isteven-multi-select.css'),
            'polyfills': path.resolve(__dirname, 'src/js/polyfills.js')
        },
        extensions: ['*', '.js', '.jsx']
    },
    node: {
        fs: 'empty',
        tls: 'empty',
        net: 'empty'
    },
    plugins: [
        new CopyWebpackPlugin([
            {
                from: './docs/**/*'
            }
        ]),
        new CssExtractPlugin({filename: "styles.[hash].bundle.css"}),
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            jquery: "jquery",
            'window.jQuery': "jquery",
            polyfills: "polyfills",
            Raven: "raven-js",
            Promise: 'bluebird'
        }),
        new webpack.NormalModuleReplacementPlugin(/es6-promise$/, 'bluebird'),
        new HtmlWebpackPlugin({
            template: 'index.ejs',
            inject: 'head'
        })
    ],
    devtool: 'source-map',
    stats: 'errors-warnings'
});