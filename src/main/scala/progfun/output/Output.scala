package progfun.output

trait Output[A,B] {
  def serialize(a:A):B
}
