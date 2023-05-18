package com.sample.ui.home
class DetailScreenState(val state: WorkingStatus, val first : Any? = null)

enum class WorkingStatus {
    Loaded,
    Loading,
}