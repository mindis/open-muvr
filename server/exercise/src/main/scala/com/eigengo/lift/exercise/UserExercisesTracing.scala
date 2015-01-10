package com.eigengo.lift.exercise

import java.io.FileOutputStream

import akka.actor.Actor.Receive
import akka.persistence.PersistentView
import scodec.bits.BitVector

import scala.util.Try

object UserExercisesTracingView {

}

class UserExercisesTracingView extends PersistentView {
  override def viewId: String = ???
  override def persistenceId: String = ???
  override def receive: Receive = ???
}

object UserExercisesTracing {

  def saveAccelerometerData(id: SessionId, datas: List[AccelerometerData]) = {
    Try {
      val fos = new FileOutputStream(s"/tmp/ad-$id.csv", true)
      datas.foreach { ad ⇒
        ad.values.foreach { v ⇒
          val line = s"${v.x},${v.y},${v.z}\n"
          fos.write(line.getBytes("UTF-8"))
        }
      }
      fos.close()
    }
  }


  def saveBits(id: SessionId, bits: BitVector): Unit = {
    Try { val fos = new FileOutputStream(s"/tmp/ad-$id.dat", true); fos.write(bits.toByteArray); fos.close() }
  }

}
