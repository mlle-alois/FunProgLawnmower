package progfun.serializer

trait OutputSerializer[A,B,C] {
  def serialize(grass:A,lawnmowers:B):C
}
