# P05: Reverse a list

require_relative "lists.rb"

# will just monkey patch list class
class List
  # by going to end and constructing in reverse order
  def self.reverse(ls)
    reversed = Nil.instance
    ls.each do |el|
      reversed = reversed << el
    end
    reversed
  end

  # in somewhat recursive manner (worse that first)
  def self.reverse2(ls)
    if ls.empty?
      ls
    else
      self.reverse2(ls.tail) >> ls.head
    end
  end
end

#test
class Tests
  def self.reverse
    ls = List["one", "two", "three", "four", "five"]
    
    puts ls
    puts List.reverse ls
    puts List.reverse2 ls
  end
end