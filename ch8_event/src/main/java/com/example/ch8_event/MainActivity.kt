package com.example.ch8_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import com.example.ch8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var pauseTime = 0L
    var initTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //[viewbinding]으로 작성 -> 주석처리 함
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //3개의 버튼에 대한 이벤트 처리
        binding.btnStart.setOnClickListener {
            //기준 시간 = 활성화된 타이머 시간 + 이전에 stop까지 기록된 시간
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()
            binding.btnStart.isEnabled = false //start를 2번 누르지 않게 false
            binding.btnStop.isEnabled = true
            binding.btnReset.isEnabled = true
        }
        binding.btnStop.setOnClickListener {
            //stop 했을 때 시간을 기억하기 위해서 변수 pauseTime 설정
            //pauseTime = 진해된 값 - 기준 시간 (얼마만큼 시간이 지난 후에 stop을 눌렀는지 기억 가능)
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.btnStart.isEnabled = true
            binding.btnStop.isEnabled = false //stop을 2번 누르지 않게 false
            binding.btnReset.isEnabled = true
        }
        binding.btnReset.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.stop()
            binding.btnStart.isEnabled = true
            binding.btnStop.isEnabled = true
            binding.btnReset.isEnabled = false //reset을 2번 누르지 않게 false
        }

    }
    //키 이벤트 : Back 키를 두번 연속으로 누른 경우에만 종료
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-initTime > 3000){ //두번째 누른 시간 - 첫번째 누른 시간
                Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_LONG).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}