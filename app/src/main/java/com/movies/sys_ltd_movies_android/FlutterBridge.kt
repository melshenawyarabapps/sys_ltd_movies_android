package com.movies.sys_ltd_movies_android

import android.content.Context
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

object FlutterBridge {

    const val TAG = "flutter_fragment"
    const val CHANNEL_NAME = "com.movies.movies_flutter/channel"
    const val ENGINE_ID = "movies_flutter_engine"

    private var flutterEngine: FlutterEngine? = null

    fun init(context: Context) {
        if (flutterEngine == null) {
            flutterEngine = FlutterEngine(context)
            flutterEngine?.dartExecutor?.executeDartEntrypoint(
                io.flutter.embedding.engine.dart.DartExecutor.DartEntrypoint.createDefault()
            )
            io.flutter.embedding.engine.FlutterEngineCache.getInstance().put(ENGINE_ID, flutterEngine)
        }
    }

    fun createFlutterFragment(): FlutterFragment {
        return FlutterFragment.withCachedEngine(ENGINE_ID).build()
    }

    fun configureChannel(engine: FlutterEngine, onMovieSelected: (Int) -> Unit) {
        MethodChannel(
            engine.dartExecutor.binaryMessenger,
            CHANNEL_NAME
        ).setMethodCallHandler { call, result ->
            if (call.method == "onMovieTap") {
                val movieId = call.argument<Int>("movieId")
                if (movieId != null) {
                    onMovieSelected(movieId)
                    result.success(null)
                } else {
                    result.error("INVALID_ARGUMENT", "movieId is null", null)
                }
            } else {
                result.notImplemented()
            }
        }
    }
}
