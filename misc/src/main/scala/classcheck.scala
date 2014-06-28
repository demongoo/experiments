/**
 * Allows checking inheritance in form $[A] >? $[B]
 */
package object classcheck {
  import scala.reflect._

  private def isDerivedFrom(cls: Class[_], parent: Class[_]): Boolean = cls match {
    case null => false
    case c if c == parent => true
    case c => isDerivedFrom(c.getSuperclass, parent) || c.getInterfaces.exists(isDerivedFrom(_, parent))
  }

  def >?[A : ClassTag, B : ClassTag]: Boolean = isDerivedFrom(implicitly[ClassTag[A]].runtimeClass, implicitly[ClassTag[B]].runtimeClass)

  class $[A: ClassTag] {
    val cls = implicitly[ClassTag[A]].runtimeClass
    def >?(other: $[_]) = isDerivedFrom(cls, other.cls)
    def >?!(other: $[_]): Unit = {
      println(s"${cls.getName} is ${if (!this.>?(other)) "not " else ""}derived from ${other.cls.getName}")
    }
  }

  object $ {
    def apply[A : ClassTag] = new $[A]
  }
}