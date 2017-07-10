package com.salexandru.CsvEncoder

import shapeless._

trait CsvEncoder[A] {
  def encode(value: A): List[String]
}

object CsvEncoder {
  def apply[A](implicit enc: CsvEncoder[A]): CsvEncoder[A] = enc

  def instance[A](f: A => List[String]): CsvEncoder[A] = (value: A) => f(value)

  implicit val csvEncoderOfBool: CsvEncoder[Boolean]    = (value: Boolean) => List(s"$value")
  implicit val csvEncoderOfShort: CsvEncoder[Short]      = (value: Short)   => List(s"$value")
  implicit val csvEncoderOfInt: CsvEncoder[Int]        = (value: Int)     => List(s"$value")
  implicit val csvEncoderOfLong: CsvEncoder[Long]       = (value: Long)    => List(s"$value")
  implicit val csvEncoderOfFloat: CsvEncoder[Float]      = (value: Float)   => List(s"$value")
  implicit val csvEncoderOfDouble: CsvEncoder[Double]     = (value: Double)  => List(s"$value")
  implicit val csvEncoderOfString: CsvEncoder[String]     = (value: String)  => List(value)

  implicit def csvEncoderOfOptional[A] (implicit enc: CsvEncoder[A]): CsvEncoder[Option[A]] = {
    instance {
      case Some(value) => enc.encode(value)
      case None => Nil
    }
  }

  implicit def csvEncoderOfPair[A, B] (implicit encOfA: CsvEncoder[A], encOfB: CsvEncoder[B]): CsvEncoder[(A,B)] = {
    instance {case (a, b) => encOfA.encode(a) ++ encOfB.encode(b) }
  }

  implicit def csvEncoderOfPair[A] (implicit enc: CsvEncoder[A]): CsvEncoder[List[A]] = {
    (value: List[A]) => value flatMap enc.encode
  }

  //decode case class
  implicit val hnillEncoder: CsvEncoder[HNil] = instance(_ => Nil)

  implicit def hListEncoder[H, T <: HList] (implicit hEncoder: Lazy[CsvEncoder[H]], tEncoder: CsvEncoder[T]): CsvEncoder[H :: T] = {
    instance { case h :: t =>  hEncoder.value.encode(h) ++ tEncoder.encode(t) }
  }

  //decode coproducts
  implicit val cnilEncoder: CsvEncoder[CNil] = instance(_ => throw new Exception("CNil reached! :((("))
  implicit def clistEncoder[H, T <: Coproduct] (implicit hEncoder: Lazy[CsvEncoder[H]], tEncoder: CsvEncoder[T]): CsvEncoder[H :+: T] = {
    instance {
      case Inl(h) => hEncoder.value.encode(h)
      case Inr(t) => tEncoder.encode(t)
    }
  }

  //now for everything that
  implicit def encoderForGenericRep[A, R] (implicit generic: Generic.Aux[A, R],  rEncoder: Lazy[CsvEncoder[R]]): CsvEncoder[A] = {
    (value: A) => rEncoder.value.encode(generic.to(value))
  }
}
