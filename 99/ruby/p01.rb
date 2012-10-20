# P01: Find the last element of a list 

require_relative "lists.rb"

# will just monkey patch list class
class List
  # last recursive
  def self.last(ls)
    if ls.empty? then raise "Last of empty list" end
    
    if ls.tail.empty?
      ls.head
    else
      self.last(ls.tail)
    end
  end
  
  # last in more imperative style
  def self.last2(ls)
    if ls.empty? then raise "Last of empty list" end
    
    until ls.tail.empty? do ls = ls.tail end
    ls.head
  end

  # through array (dirty)
  def self.last3(ls); ls.to_a.last end
end

#test
class Tests
  def self.last
    ls = List["one", "two", "three", "four", "five"]
    
    puts ls
    puts List.last ls
    puts List.last2 ls
    puts List.last3 ls
  end
end