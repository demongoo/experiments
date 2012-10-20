# P04: Find the number of elements of a list

require_relative "lists.rb"

# will just monkey patch list class
class List
  # length tail :) recursive
  def self.length(ls)
    step = lambda { |acc, xs|
      if xs.empty?
        acc
      else
        step.call(acc + 1, xs.tail)
      end
    }

    step.call(0, ls)
  end
  
  # in looping style
  def self.length2(ls)
    length = 0
    until ls.empty? do
      ls = ls.tail
      length += 1
    end
    length
  end

  # reducing
  def self.length3(ls); ls.reduce(0) { |m| m + 1 } end

  # dirty
  def self.length4(ls); ls.to_a.length end;

  # length recursive but proc instead of lambda, not a big deal
  def self.length5(ls)
    step = Proc.new do |acc, xs|
      if xs.empty?
        acc
      else
        step.call(acc + 1, xs.tail)
      end
    end

    step.call(0, ls)
  end
end

#test
class Tests
  def self.length
    ls = List["one", "two", "three", "four", "five"]
    
    puts ls
    puts List.length ls
    puts List.length2 ls
    puts List.length3 ls
    puts List.length4 ls
    puts List.length5 ls
  end
end