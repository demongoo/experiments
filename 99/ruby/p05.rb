# P05: Reverse a list

require_relative "lists.rb"

# will just monkey patch list class
class List
  # by going to end and appending
  def self.reverse(ls)
    reversed = Nil.instance
    ls.each do |el|
      reversed = reversed << el
    end
    reversed
  end
end

#test
class Tests
  def self.reverse
    ls = List["one", "two", "three", "four", "five"]
    
    puts ls
    puts List.reverse ls
  end
end