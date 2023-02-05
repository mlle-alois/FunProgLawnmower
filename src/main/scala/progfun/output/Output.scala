package progfun.output

trait Output[A,B,C] {
  def serialize(grass:A,lawnmowers:B):C
}
