/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var r = {'special': /[^A-Za-z1234567890\s\t]/g,
    'nombre': /[´ªº¨]|['¡¿?!1234567890]|[^A-Za-z'áéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛÑñäëïöüÄËÏÖÜ\s\t]/g};

function valida_caracteres(o, w) {

    o.value = o.value.replace(r[w], '');
}