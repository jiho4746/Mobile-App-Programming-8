package com.example.myapplication8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //터치이벤트
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){ // ? : NULL 허용
            // ACTION_DOWN일 떄, Log창에 출력 (event의 위치) -> 변수는 ${}로!!
            MotionEvent.ACTION_DOWN -> {
                Log.d("mobileApp", "Action DOWN : ${event.x}, ${event.y}")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("mobileApp", "Action UP : ${event.rawX}, ${event.rawY}")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("mobileApp", "Action MOVE")
            }
        }
        return super.onTouchEvent(event)
    }
    //키 이벤트 (발생 시 종류에 따라 콜백 함수가 자동 호출됨)
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("mobileApp", "Key Down")
        when(keyCode){
            KeyEvent.KEYCODE_0 -> {Log.d("mobileApp", "0 key Down")}
            KeyEvent.KEYCODE_B -> {Log.d("mobileApp", "B key Down")}
            //KeyEvent.KEYCODE_BACK -> {Log.d("mobileApp", "뒤로가기 key Down")}
            KeyEvent.KEYCODE_VOLUME_UP -> {Log.d("mobileApp", "VOLUME UP")}
            KeyEvent.KEYCODE_VOLUME_DOWN -> {Log.d("mobileApp", "VOLUME DOWN")}
        }
        return super.onKeyDown(keyCode, event)
    }
    //키 이벤트
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("mobileApp", "Key Up")
        return super.onKeyUp(keyCode, event)
    }
    //뒤로 가는 경우에는 함수가 별도로 있음
    override fun onBackPressed() {
        Log.d("mobileApp", "뒤로가기 key Down")
    }
}