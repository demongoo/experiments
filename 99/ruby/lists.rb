# Basic implementation of List for 99 problems
#
# Examples:
#   List[1,2,3,4,5]
#   List[] (would equal Nil.instance)
#   ls = List[]; ls = ls << "String" << "More" (would be List[More, String])
#   ls = List[]; ls = ls >> "String" >> "More" (would be List[String, More])

require "singleton"

class List
  include Enumerable

  attr_reader :head, :tail

  # empty - abstract
  def empty?; raise "Not Implemented" end

  # each
  def each(&block)
    unless self.empty?
      block.call(@head)
      @tail.each(&block)
    end
  end

  # comparison
  def <=>(that)
    self.head <=> that.head
  end

  # add element (prepend)
  def <<(elem)
    Cons.new(elem, self)
  end

  # add element (append)
  def >>(elem)
    # assuming the list is immutable copy needed
    nls = Nil.instance
    last = Cons.new(elem, Nil.instance)
    # recursive from the end
    step = lambda { |xs|
      if xs.empty?
        last
      else
        step.call(xs.tail) << xs.head
      end
    }
    # run
    step.call(self)
  end

  # to string
  def to_s
    "List[" + (self.map { |i| i.to_s }).join(', ') + "]"
  end

  # static factory to create lists
  def self.[](*args)
    ls = Nil.instance

    i = args.length - 1
    while i >= 0
      ls = ls << args[i]
      i -= 1
    end

    ls
  end
end

# Cons (non-empty element of a List)
class Cons < List
  # constructor
  def initialize(head, tail)
    @head = head
    @tail = tail
  end

  def empty?; false end
end

# Nil element (Empty list)
class Nil < List
  include Singleton

  def head; raise "Head of empty List" end
  def tail; raise "Tail of empty List" end
  def empty?; true end
end