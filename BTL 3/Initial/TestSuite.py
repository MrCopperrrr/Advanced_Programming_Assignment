import unittest
from TestUtils import TestUtils
# from SymbolTable import simulate # import để debug

class TestSymbolTable(unittest.TestCase):

    # Test 1: Thêm 1 biến hợp lệ
    def test_1(self):
        input = ["INSERT a number"]
        expected = ["success"]
        self.assertTrue(TestUtils.check(input, expected, 101))

    # Test 2: Thêm 2 biến hợp lệ, không trùng tên
    def test_2(self):
        input = ["INSERT a number", 
                "INSERT b string"
        ]
        expected = ["success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 102))

    # Test 3: Thêm biến bị trùng tên – lỗi Redeclared
    def test_3(self):
        input = ["INSERT a number", 
                 "INSERT a number"
        ]
        expected = ["Redeclared: INSERT a number"]
        self.assertTrue(TestUtils.check(input, expected, 103))

    # Test 4: Thêm biến trùng tên ở scope khác – hợp lệ
    def test_4(self):
        input = ["INSERT x number", 
                "BEGIN", 
                "INSERT x number", 
                "END"
        ]
        expected = ["success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 104))

    # Test 5: Thêm 2 biến trùng tên – khác kiểu – lỗi Redeclared
    def test_5(self):
        input = ["INSERT x number", 
                 "INSERT x string"
        ]
        expected = ["Redeclared: INSERT x string"]
        self.assertTrue(TestUtils.check(input, expected, 105))

    # Test 6: Thêm nhiều biến hợp lệ liên tiếp
    def test_6(self):
        input = ["INSERT a1 number", 
                 "INSERT b2 string", 
                 "INSERT c3 number", 
                 "INSERT d4 string"
        ]
        expected = ["success", "success", "success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 106))

    # Test 7: Thêm biến có tên hợp lệ với chữ hoa, số, gạch dưới
    def test_7(self):
        input = ["INSERT a_B1 number"]
        expected = ["success"]
        self.assertTrue(TestUtils.check(input, expected, 107))

    # Test 8: Trùng tên biến trong scope con – hợp lệ
    def test_8(self):
        input = ["INSERT x number", 
                 "BEGIN", 
                 "INSERT x number", 
                 "END"
        ]
        expected = ["success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 108))

    # Test 9: Trùng tên biến trong cùng scope – lỗi
    def test_9(self):
        input = [
            "INSERT y string", 
            "INSERT y string"
        ]
        expected = ["Redeclared: INSERT y string"]
        self.assertTrue(TestUtils.check(input, expected, 109))

    # Test 10: Insert nhiều biến không trùng tên trong nhiều block lồng nhau
    def test_10(self):
        input = [
            "INSERT a number",
            "BEGIN",
            "INSERT b string",
            "BEGIN",
            "INSERT c number",
        ]
        expected = ["UnclosedBlock: 2"]
        self.assertTrue(TestUtils.check(input, expected, 110))

    # Test 11: Insert biến đã tồn tại trong cùng block => lỗi Redeclared
    def test_11(self):
        input = [
            "INSERT x number",
            "INSERT x string"
        ]
        expected = ["Redeclared: INSERT x string"]
        self.assertTrue(TestUtils.check(input, expected, 111))

    # Test 12: Insert biến giống tên trong block cha => lỗi UnclosedBlock
    def test_12(self):
        input = [
            "INSERT x number",
            "BEGIN",
            "INSERT x string"
        ]
        expected = ["UnclosedBlock: 1"]
        self.assertTrue(TestUtils.check(input, expected, 112))

    # Test 13: Insert biến giống tên ở block ông nội => lỗi UnclosedBlock
    def test_13(self):
        input = [
            "INSERT x number",
            "BEGIN",
            "BEGIN",
            "INSERT x string",
            "END"
        ]
        expected = ["UnclosedBlock: 1"]
        self.assertTrue(TestUtils.check(input, expected, 113))

    # Test 14: Insert liên tiếp nhiều biến cùng kiểu trong cùng 1 block
    def test_14(self):
        input = [
            "INSERT x number",
            "INSERT y number",
            "INSERT z number"
        ]
        expected = ["success", "success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 114))

    # Test 15: Insert sau BEGIN và kết thúc bằng END đúng => success
    def test_15(self):
        input = [
            "BEGIN",
            "INSERT x number",
            "END"
        ]
        expected = ["success"]
        self.assertTrue(TestUtils.check(input, expected, 115))

    # Test 16: Insert trong block bị thiếu END => lỗi UnclosedBlock
    def test_16(self):
        input = [
            "BEGIN",
            "INSERT x number"
        ]
        expected = ["UnclosedBlock: 1"]
        self.assertTrue(TestUtils.check(input, expected, 116))

    # Test 17: ASSIGN biến chưa khai báo => lỗi Undeclared
    def test_17(self):
        input = [
            "ASSIGN x 10"
        ]
        expected = ["Undeclared: ASSIGN x 10"]
        self.assertTrue(TestUtils.check(input, expected, 117))

    # Test 18: ASSIGN đúng kiểu => success
    def test_18(self):
        input = [
            "INSERT x number",
            "ASSIGN x 100"
        ]
        expected = ["success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 118))

    # Test 19: ASSIGN sai kiểu (gán string cho number) => TypeMismatch
    def test_19(self):
        input = [
            "INSERT x number",
            "ASSIGN x 'hello'"
        ]
        expected = ["TypeMismatch: ASSIGN x 'hello'"]
        self.assertTrue(TestUtils.check(input, expected, 119))

    # Test 20: ASSIGN biến kiểu string với chuỗi hợp lệ => success
    def test_20(self):
        input = [
            "INSERT s string",
            "ASSIGN s 'abc'"
        ]
        expected = ["success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 120))

    # Test 21: ASSIGN biến kiểu string với số => TypeMismatch
    def test_21(self):
        input = [
            "INSERT s string",
            "ASSIGN s 123"
        ]
        expected = ["TypeMismatch: ASSIGN s 123"]
        self.assertTrue(TestUtils.check(input, expected, 121))

    # Test 22: ASSIGN biến chưa được khai báo => Undeclared
    def test_22(self):
        input = [
            "ASSIGN y 'hello'"
        ]
        expected = ["Undeclared: ASSIGN y 'hello'"]
        self.assertTrue(TestUtils.check(input, expected, 122))

    # Test 23: ASSIGN giữa hai biến cùng kiểu => success
    def test_23(self):
        input = [
            "INSERT a number",
            "INSERT b number",
            "ASSIGN a 10",
            "ASSIGN b a"
        ]
        expected = ["success", "success", "success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 123))

    # Test 24: ASSIGN giữa hai biến khác kiểu => TypeMismatch
    def test_24(self):
        input = [
            "INSERT a string",
            "INSERT z number",
            "ASSIGN a 123"
        ]
        expected = ["TypeMismatch: ASSIGN a 123"]
        self.assertTrue(TestUtils.check(input, expected, 124))

    # Test 25: ASSIGN từ biến chưa khai báo => Undeclared
    def test_25(self):
        input = [
            "INSERT a number",
            "ASSIGN a 'b'"
        ]
        expected = ["TypeMismatch: ASSIGN a 'b'"]
        self.assertTrue(TestUtils.check(input, expected, 125))

    # Test 26: ASSIGN biến trong block con => success nếu hợp lệ
    def test_26(self):
        input = [
            "INSERT x number",
            "BEGIN",
            "ASSIGN x 5"
        ]
        expected = ["UnclosedBlock: 1"]
        self.assertTrue(TestUtils.check(input, expected, 126))

    # Test 27: ASSIGN biến trong block cha => success nếu hợp lệ
    def test_27(self):
        input = [
            "INSERT x string",
            "BEGIN",
            "BEGIN",
            "ASSIGN x 'a'"
        ]
        expected = ["UnclosedBlock: 2"]
        self.assertTrue(TestUtils.check(input, expected, 127))

    # Test 28: Gán một chuỗi rỗng cho biến kiểu string => success
    def test_28(self):
        input = [
            "INSERT s string",
            "ASSIGN s ''"
        ]
        expected = ["success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 128))

    # Test 29: Gán biểu thức với số (chưa hỗ trợ toán tử, nên đây là hằng) => success
    def test_29(self):
        input = [
            "INSERT n number",
            "ASSIGN n 999"
        ]
        expected = ["success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 129))

    # Test 30: BEGIN và END đúng cặp => success
    def test_30(self):
        input = [
            "BEGIN",
            "END"
        ]
        expected = []
        self.assertTrue(TestUtils.check(input, expected, 130))

    # Test 31: BEGIN lồng BEGIN và END đúng cặp => success
    def test_31(self):
        input = [
            "BEGIN",
            "BEGIN",
            "END",
            "END"
        ]
        expected = []
        self.assertTrue(TestUtils.check(input, expected, 131))

    # Test 32: BEGIN nhưng thiếu END => UnclosedBlock
    def test_32(self):
        input = [
            "BEGIN",
            "INSERT x number"
        ]
        expected = ["UnclosedBlock: 1"]
        self.assertTrue(TestUtils.check(input, expected, 132))

    # Test 33: 2 BEGIN nhưng chỉ có 1 END => UnclosedBlock: 1
    def test_33(self):
        input = [
            "BEGIN",
            "BEGIN",
            "END"
        ]
        expected = ["UnclosedBlock: 1"]
        self.assertTrue(TestUtils.check(input, expected, 133))

    # Test 34: END mà không có BEGIN => UnknownBlock
    def test_34(self):
        input = [
            "END"
        ]
        expected = ["UnknownBlock"]
        self.assertTrue(TestUtils.check(input, expected, 134))

    # Test 35: BEGIN + INSERT + END, đảm bảo INSERT không lỗi trong block
    def test_35(self):
        input = [
            "BEGIN",
            "INSERT x number",
            "END"
        ]
        expected = ["success"]
        self.assertTrue(TestUtils.check(input, expected, 135))

    # Test 36: Khai báo lại biến trong block cha => Redeclared
    def test_36(self):
        input = [
            "INSERT x number",
            "INSERT x string"
        ]
        expected = ["Redeclared: INSERT x string"]
        self.assertTrue(TestUtils.check(input, expected, 136))

    # Test 37: Khai báo lại biến trong block con => success
    def test_37(self):
        input = [
            "INSERT x number",
            "BEGIN",
            "INSERT x number"
        ]
        expected = ["UnclosedBlock: 1"]
        self.assertTrue(TestUtils.check(input, expected, 137))

    # Test 38: Redeclared trong cùng block con => lỗi
    def test_38(self):
        input = [
            "BEGIN",
            "INSERT y string",
            "INSERT y string"
        ]
        expected = ["Redeclared: INSERT y string"]
        self.assertTrue(TestUtils.check(input, expected, 138))

    # Test 39: Khai báo biến trong block rồi END => biến đó không còn nữa
    def test_39(self):
        input = [
            "BEGIN",
            "INSERT tmp number",
            "END",
            "LOOKUP tmp"
        ]
        expected = ["Undeclared: LOOKUP tmp"]
        self.assertTrue(TestUtils.check(input, expected, 139))

    # Test 40: LOOKUP biến tồn tại trong global scope => trả về 0
    def test_40(self):
        input = [
            "INSERT a number",
            "LOOKUP a"
        ]
        expected = ["success", "0"]
        self.assertTrue(TestUtils.check(input, expected, 140))

    # Test 41: LOOKUP biến không tồn tại => trả về -1
    def test_41(self):
        input = [
            "LOOKUP b"
        ]
        expected = ["Undeclared: LOOKUP b"]
        self.assertTrue(TestUtils.check(input, expected, 141))

    # Test 42: LOOKUP biến trong block con => trả về scope mức 1
    def test_42(self):
        input = [
            "BEGIN",
            "INSERT x number",
            "LOOKUP x"
        ]
        expected = ["UnclosedBlock: 1"]
        self.assertTrue(TestUtils.check(input, expected, 142))

    # Test 43: LOOKUP biến không có trong block con nhưng có ở cha => trả về scope mức 0
    def test_43(self):
        input = [
            "INSERT x number",
            "BEGIN",
            "LOOKUP x"
        ]
        expected = ["UnclosedBlock: 1"]
        self.assertTrue(TestUtils.check(input, expected, 143))

    # Test 44: PRINT biến trong 2 scope khác nhau => chỉ in biến trong scope hiện tại
    def test_44(self):
        input = [
            "INSERT a number",
            "BEGIN",
            "INSERT a number",
            "INSERT b string",
            "PRINT",
            "END"
        ]
        expected = ["success", "success", "success", "a//1 b//1"]
        self.assertTrue(TestUtils.check(input, expected, 144))

    # Test 45: PRINT trong global scope => in tất cả biến global
    def test_45(self):
        input = [
            "INSERT x number",
            "INSERT y string",
            "PRINT"
        ]
        expected = ["success", "success", "x//0 y//0"]
        self.assertTrue(TestUtils.check(input, expected, 145))

    # Test 46: RPRINT với biến ở 2 scope khác nhau => biến ở scope con che biến ở cha
    def test_46(self):
        input = [
            "INSERT a number",
            "BEGIN",
            "INSERT a string",
            "INSERT b number",
            "RPRINT",
            "END"
        ]
        expected = ["success", "success", "success", "b//1 a//1"]
        self.assertTrue(TestUtils.check(input, expected, 146))

    # Test 47: PRINT không có biến => in rỗng
    def test_47(self):
        input = [
            "PRINT"
        ]
        expected = []
        self.assertTrue(TestUtils.check(input, expected, 147))

    # Test 48: RPRINT không có biến => in rỗng
    def test_48(self):
        input = [
            "RPRINT"
        ]
        expected = []
        self.assertTrue(TestUtils.check(input, expected, 148))

    # Test 49: LOOKUP biến được khai báo ở block đã kết thúc => -1
    def test_49(self):
        input = [
            "BEGIN",
            "INSERT x number",
            "END",
            "LOOKUP x"
        ]
        expected = ["Undeclared: LOOKUP x"]
        self.assertTrue(TestUtils.check(input, expected, 149))

    # Test 50: ASSIGN thiểu dấu nháy đóng
    def test_50(self):
        input = [
            "INSERT s string",
            "ASSIGN s 'abc" 
        ]
        expected = ["Invalid: ASSIGN s 'abc"]
        self.assertTrue(TestUtils.check(input, expected, 150))

    # Test 51: ASSIGN string lỗi _
    def test_51(self):
        input = [
            "INSERT s string",
            "ASSIGN s 'abc_1'"
        ]
        expected = ["Invalid: ASSIGN s 'abc_1'"]
        self.assertTrue(TestUtils.check(input, expected, 151))

    # Test 52: ASSIGN string rỗng  
    def test_52(self):
        input = [
            "INSERT s string",
            "ASSIGN s ''"
        ]
        expected = ["success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 152))

    # Test 53: ASSIGN thiểu dấu nháy mở
    def test_53(self):
        input = [
            "INSERT s string",
            "ASSIGN s abc'" 
        ]
        expected = ["Invalid: ASSIGN s abc'"]
        self.assertTrue(TestUtils.check(input, expected, 153))

    # Test 54: ASSIGN string không có dấu nháy
    def test_54(self):
        input = [
            "INSERT s string",
            "ASSIGN s abc" 
        ]
        expected = ["Undeclared: ASSIGN s abc"]
        self.assertTrue(TestUtils.check(input, expected, 154))

    # Test 55: Không có lệnh nào
    def test_55(self):
        input = []
        expected = []
        self.assertTrue(TestUtils.check(input, expected, 155))

    # Test 56: 
    def test_56(self):
        input = [
            "INSERT x number",
            "INSERT y string",
            "BEGIN",
            "INSERT x number",
            "INSERT z number",
            "PRINT",
            "END" 
        ]
        expected = ["success", "success", "success", "success", "y//0 x//1 z//1"]
        # print(simulate(input))
        self.assertTrue(TestUtils.check(input, expected, 156))

    # Test 57: 
    def test_57(self):
        input = [
            "INSERT x number",
            "INSERT y string",
            "BEGIN",
            "INSERT x number",
            "INSERT z number",
            "RPRINT",
            "END" 
        ]
        expected = ["success", "success", "success", "success", "z//1 x//1 y//0"]
        self.assertTrue(TestUtils.check(input, expected, 157))

    # Test 58: PRINT với nhiều level lồng nhau
    def test_58(self):
        input = [
            "INSERT a number", #a0
            "BEGIN",
            "INSERT b string", #a0 b1
            "BEGIN",
            "INSERT c number", # a0 b1 c2
            "INSERT a string", # a0 b1 c2 a2
            "PRINT", # b1 c2 a2
            "END",
            "END"
        ]
        expected = ["success", "success", "success", "success", "b//1 c//2 a//2"]
        self.assertTrue(TestUtils.check(input, expected, 158))

    # Test 59: RPRINT với nhiều level lồng nhau
    def test_59(self):
        input = [
            "INSERT a number", # a0
            "BEGIN",
            "INSERT b string", # a0 b1
            "BEGIN",
            "INSERT c number", # a0 b1 c2
            "INSERT a string", # a0 b1 c2 a2
            "RPRINT", # a2 c2 b1
            "END",
            "END"
        ]
        expected = ["success", "success", "success", "success", "a//2 c//2 b//1"]
        self.assertTrue(TestUtils.check(input, expected, 159))

    # Test 60: PRINT trong scope trống
    def test_60(self):
        input = [
            "BEGIN",
            "PRINT",
            "END"
        ]
        expected = []
        self.assertTrue(TestUtils.check(input, expected, 160))

    # Test 61: ASSIGN chuỗi có khoảng trắng
    def test_61(self):
        input = [
            "INSERT s string",
            "ASSIGN s 'HelloWorld'"
        ]
        expected = ["success", "success"]
        self.assertTrue(TestUtils.check(input, expected, 161))

    # Test 62: PRINT với nhiều biến cùng tên ở nhiều scope khác nhau
    def test_62(self):
        input = [
            "INSERT x number",
            "BEGIN",
            "INSERT y string",
            "BEGIN",
            "INSERT x string",
            "PRINT",
            "END",
            "END"
        ]
        expected = ["success", "success", "success", "y//1 x//2"]
        self.assertTrue(TestUtils.check(input, expected, 162))

    # Test 63: LOOKUP biến trong scope lồng sâu
    def test_63(self):
        input = [
            "INSERT x number",
            "BEGIN",
            "BEGIN",
            "BEGIN",
            "LOOKUP x"
        ]
        expected = ["UnclosedBlock: 3"]
        self.assertTrue(TestUtils.check(input, expected, 163))

    # Test 64: Xử lý nhiều block BEGIN-END lồng nhau
    def test_64(self):
        input = [
            "BEGIN",
            "BEGIN",
            "BEGIN",
            "END",
            "END",
            "END"
        ]
        expected = []
        self.assertTrue(TestUtils.check(input, expected, 164))

    # Test 65: PRINT và RPRINT trên cùng một trạng thái Symbol Table
    def test_65(self):
        input = [
            "INSERT x number",
            "INSERT y string",
            "BEGIN",
            "INSERT z number",
            "PRINT",
            "RPRINT",
            "END"
        ]
        expected = ["success", "success", "success", "x//0 y//0 z//1", "z//1 y//0 x//0"]
        self.assertTrue(TestUtils.check(input, expected, 165))
    
    def test_complex_master_case(self):
        input = [
            "INSERT a number",           # a//0
            "INSERT b string",           # b//0
            "ASSIGN a 100",              # OK
            "ASSIGN b 'hello'",          # OK
            "BEGIN",                     # scope 1
            "INSERT c number",           # c//1
            "ASSIGN c a",                # OK (a is number)
            "LOOKUP b",                  # should return 0 (found in outer scope)
            "BEGIN",                     # scope 2
            "INSERT a string",          # shadowing a, a//2
            "ASSIGN a b",                # OK (string to string)
            "BEGIN",                     # scope 3
            "INSERT d string",          # d//3
            "ASSIGN d 'data123'",       # OK
            "PRINT",                    # should show: a//2 b//0 c//1 d//3
            "END",                       # exit scope 3
            "RPRINT",                   # should show: c//1 a//2 b//0
            "END",                       # exit scope 2
            "PRINT",                    # should show: a//0 b//0 c//1
            "END",                       # exit scope 1
            "LOOKUP c",                  # error: Undeclared
            "INSERT b number",           # error: Redeclared (b already in scope 0)
            "ASSIGN a 'oops'",           # error: TypeMismatch
            "ASSIGN x 123",              # error: Undeclared
            "END",                       # error: UnknownBlock
            "BEGIN",                     # unclosed BEGIN
            "INSERT e number",          # e//1
            "LOOKUP a",                  # should return 0
            "ASSIGN e a",                # OK (number to number)
        ]

        expected = ["Undeclared: LOOKUP c"]
        # print(simulate(input))
        self.assertTrue(TestUtils.check(input, expected, 999))  # test ID 999 for chaos case
        
    def test_unbalanced_block_too_many_end(self):
            input = [
                "INSERT x number",
                "BEGIN",
                "INSERT y string",
                "END",
                "END"  
            ]
            expected = ["UnknownBlock"]

            #print(simulate(input))
            self.assertTrue(TestUtils.check(input, expected, 1001))

    def test_complex_with_first_error_redeclared(self):
        input = [
            "INSERT a number",          
            "INSERT a string",          
            "INSERT b string",
            "BEGIN",
            "INSERT b string",
            "ASSIGN b 'hello'",
            "ASSIGN a 10",
            "BEGIN",
            "INSERT c number",
            "ASSIGN c a",
            "PRINT",
            "END",
            "RPRINT",
            "END"
        ]

        expected = ["Redeclared: INSERT a string"]  

        #print(simulate(input))
        self.assertTrue(TestUtils.check(input, expected, 1000))

