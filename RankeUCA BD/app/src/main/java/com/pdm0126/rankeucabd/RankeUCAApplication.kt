package com.pdm0126.rankeucabd

import android.app.Application
import com.pdm0126.rankeucabd.data.AppProvider

class RankeUCAApplication : Application() {
    val appProvider by lazy { AppProvider(this) }
}