def shift_characters(char, shift):
    right = ord('a') + ((abs(ord('a') - ord(char)) + shift) % 26)
    left = ord('a') + ((abs(ord('a') - ord(char)) + -shift) % 26)
    return {'left': chr(left), 'right': chr(right)}
