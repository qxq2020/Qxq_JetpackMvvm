package me.hgj.jetpackmvvm.demo.app.weight.loadCallBack


import com.kingja.loadsir.callback.Callback
import com.qxq.qxq_jetpackmvvm.R


class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}