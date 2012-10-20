# P02: Find the last but one element of a list 

require_relative "lists.rb"

# will just monkey patch list class
class List
  # penultimate recursive
  def self.penultimate(ls)
    if ls.empty? || ls.tail.empty? then raise "No such element" end
    
    if ls.tail.tail.empty?
      ls.head
    else
      self.penultimate(ls.tail)
    end
  end
  
  # in looping style
  def self.penultimate2(ls)
    if ls.empty? || ls.tail.empty? then raise "No such element" end
    
    until ls.tail.tail.empty? do ls = ls.tail end
    ls.head
  end

  # array
  def self.penultimate3(ls); ls.to_a.fetch(-2) end
end

#test
class Tests
  def self.penultimate
    ls = List["one", "two", "three", "four", "five"]
    
    puts ls
    puts List.penultimate ls
    puts List.penultimate2 ls
    puts List.penultimate3 ls
  end
end