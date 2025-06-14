#########################
# a)

def particionar_en_dos_conjuntos_igual_suma?(nums)
  suma_total = nums.sum
  return false if suma_total.odd? # Si la suma total es impar, no se puede dividir en dos partes iguales

  objetivo = suma_total / 2
  fill_dp
  dp[objetivo]
end

def fill_dp
  dp = Array.new(objetivo + 1, false)
  dp[0] = true # Se puede formar la suma 0 con ningún elemento

  nums.each do |num|
    objetivo.downto(num) do |j|
      dp[j] ||= dp[j - num]
    end
  end
end

#########################
# b)

def anagramas?(str1, str2)
  return true if str1 == str2

  str1.each_char do |c|
    return false unless str2.include?(c)
  end

  true
end

#########################
# c)

def descomponer_factores_primos(num)
  require 'prime'

  num = -num if n.negative?
  array_res = []
  decr_incr while num > 1
  array_res
end

def decr_incr
  if (num % i).zero?
    array_res.append(i)
    num / i
  else
    i + 1
  end
end

#########################
# d)

def substring?(fixed, comparable)
  # fixed.include?(comparable)
  boolres = true
  fixed.each_char do |c_fix|
    boolres = contains?(comparable, c_fix) && boolres
  end
  boolres
end

def contains?(str, character)
  str.each_char { |c| return true if character.eql?(c) }
  false
end

#########################
# e)

def occurrency_hash
  ('a'..'z').map { |letter| [letter, 0] }.to_h
end

def remove_zero_occurrencies(hash)
  hash.select { |_key, value| value.positive? }
end

def subsequence?(fixed, comparable)
  ocurr_hash_fixed = occurrency_hash
  ocurr_hash_comparable = occurrency_hash

  fixed.each_char { |letter_fixed| ocurr_hash_fixed[letter_fixed] += 1 }
  comparable.each_char { |letter_comparable| ocurr_hash_comparable[letter_comparable] += 1 }

  ocurr_hash_fixed = remove_zero_occurrencies(ocurr_hash_fixed)
  ocurr_hash_comparable = remove_zero_occurrencies(ocurr_hash_comparable)

  ocurr_hash_comparable.each do |lett_comp, occ_comp|
    return false if ocurr_hash_fixed[lett_comp].nil? || ocurr_hash_fixed[lett_comp] < occ_comp
  end

  true
end

#########################
# f)

def k_max_sec(sec, k)
  return nil if k > sec.length

  max_found = 0
  while max_found < k
    num_max = sec.max
    delete_first_occurrence(sec, num_max)
    max_found += 1
  end
  num_max
  # sorted_sec = sec.sort.reverse
  # sorted_sec[k - 1]
end

def delete_first_occurrence(sec, obj)
  i = 0
  copy_sec = sec.dup
  while i < copy_sec.length
    return sec.delete_at(i) if copy_sec[i].eql?(obj)

    i += 1
  end
  false
end
