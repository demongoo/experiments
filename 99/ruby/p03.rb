# P03: Find the Kth element of a list

require_relative "lists.rb"

# will just monkey patch list class
class List
  # nth recursive
  def self.nth(n, ls)
    if ls.empty? then raise "No such element" end
    
    if n == 0
      ls.head
    else
      self.nth(n - 1, ls.tail)
    end
  end
  
  # in looping style
  def self.nth2(n, ls)
    loop do
      if ls.empty? || ls.tail.empty? then raise "No such element" end
      if n == 0
        break
      else
        ls = ls.tail
        n -= 1
      end
    end

    ls.head
  end

  # via array representation
  def self.nth3(n, ls); ls.to_a.fetch(n) end
end

#test
class Tests
  def self.nth
    ls = List["one", "two", "three", "four", "five"]
    
    puts ls
    puts List.nth 2, ls
    puts List.nth2 2, ls
    puts List.nth3 2, ls
    puts List.nth 5, ls
  end
end