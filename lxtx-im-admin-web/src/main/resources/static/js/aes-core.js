/**
 * aes加密
 * @param source    内容
 * @param key   密钥
 * @param iv    偏移量（null）
 * @returns {*}
 */
function encrypt(source, key, iv) {
    var key = CryptoJS.enc.Utf8.parse(key); //16位
    var encrypted = '';
    if (typeof(source) == 'object') {//对象格式的转成json字符串
        source = JSON.stringify(source);
    }
    var content = CryptoJS.enc.Utf8.parse(source);
    if (iv == null) {
        encrypted = CryptoJS.AES.encrypt(content, key, {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        });
    } else {
        var iv = CryptoJS.enc.Utf8.parse(iv);
        encrypted = CryptoJS.AES.encrypt(content, key, {
            iv: iv,
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        });
    }
    return CryptoJS.enc.Base64.stringify(CryptoJS.enc.Hex.parse(encrypted.ciphertext.toString()));
}
