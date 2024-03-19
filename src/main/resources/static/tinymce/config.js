tinymce.init({
    selector: 'textarea#content',
    // theme : "advanced",
    height: 350,
    width:"",
    plugins: [
        "codemirror advlist autolink lists link image charmap print preview hr anchor pagebreak",
        "searchreplace wordcount visualblocks visualchars fullscreen",
        "insertdatetime media nonbreaking save table contextmenu directionality",
        "emoticons template paste textcolor colorpicker textpattern imagetools code fullscreen"
    ],
    toolbar1: "undo redo bold italic underline strikethrough cut copy paste| alignleft aligncenter alignright alignjustify bullist numlist outdent indent blockquote searchreplace | table | hr removeformat | subscript superscript | charmap emoticons ltr rtl | spellchecker | visualchars visualblocks nonbreaking template pagebreak restoredraft | link unlink anchor image media | insertdatetime preview | forecolor backcolor print fullscreen code",
    toolbar2: "styleselect formatselect fontselect fontsizeselect",
    image_advtab: true,
    menubar: false,
    code_dialog_height: 400,
    encoding: 'html',
    entity_encoding : 'raw', //Sửa lỗi khi hiển thị code có dấu tiếng việt
    schema: 'html5',
    toolbar_items_size: 'small',
    relative_urls: false, 
    cleanup_on_startup: false,
    trim_span_elements: false,
    verify_html: false,
    cleanup: false,
    convert_urls: false,
    element_format : 'html',
    remove_script_host : false,
    force_br_newlines : true,
    force_p_newlines : false,
    forced_root_block : '',
    filemanager_title:"Quản lý ảnh",	
    external_filemanager_path: base_url()+"/file/",
    external_plugins: { 
        "filemanager" : base_url()+"/file/plugin.min.js",
        codemirror: base_url()+'/public/backend/tinymce/plugins/codemirror/plugin.js'
    },
    filemanager_access_key:akey(),
    codemirror: {
        indentOnInit: true,
        path: 'codemirror-4.8',
        width: 800,         // Default value is 800
        height: 450,        // Default value is 550
        saveCursorPosition: true,   // Insert caret marker
        config: {
            // mode: 'application/x-httpd-php',
            lineNumbers: true       
        },
        jsFiles: [          // Additional JS files to load
           // 'mode/clike/clike.js',
           // 'mode/php/php.js'
        ],
        cssFiles: [
           'theme/base16-dark.css',
           'theme/xq-dark.css',
           // 'theme/elegant.css'
        ]
    },
});

tinymce.init({
	 selector: 'textarea#desc',
	 toolbar_items_size: 'small',
	 height: 130,
	 width:"",
	 menubar: false,
	 plugins: [
		"advlist autolink lists link image charmap print preview hr anchor pagebreak",
		"searchreplace wordcount visualblocks visualchars fullscreen",
		"insertdatetime media nonbreaking save table contextmenu directionality",
		"emoticons template paste textcolor colorpicker textpattern imagetools code fullscreen"
	],
	toolbar1: "undo redo bold italic underline | alignleft aligncenter alignright alignjustify bullist numlist outdent indent blockquote link unlink anchor | preview | forecolor backcolor fullscreen code",
	image_advtab: true,
	menubar: false,
	code_dialog_height: 200,
	encoding: 'html',
	entity_encoding : 'raw', //Sửa lỗi khi hiển thị code có dấu tiếng việt
	schema: 'html5',
	toolbar_items_size: 'small',
    relative_urls: false,
    remove_script_host : false,
		filemanager_title:"Quản lý ảnh",	
	external_filemanager_path: base_url()+"/file/",
	external_plugins: { "filemanager" : base_url()+"/file/plugin.min.js"},
	filemanager_access_key:akey(),

});
var thumb = $(".thumb");
var remove = $(".remove-thumb");

var mo_img = base_url()+'/public/images/no-ig.png';
if (typeof old_img != 'undefined')
{
	mo_img = old_img !='' ? old_img : mo_img;
    $('input.img-link').val(mo_img);
    $('img.img-thumb').attr('src',mo_img);
}

thumb.on("click", function (e) {
    e.preventDefault();
    var field = $(this).data("field");
    var view = $(this).data("view");
    var src = base_url()+'/file/dialog.php?field_id='+field+'&akey='+akey();
    var f = document.createElement('iframe');
    f.style.width = "100%";
    f.style.height = "500px";
    f.style.border = "none";
    f.style.overflowX = "none";
    f.style.overflowY = "auto";
    f.src = src;
    
    $('#modal-file .modal-body').html(f);
    $('#modal-file').modal('show');
    $('#modal-file').on('hide.bs.modal',function(){
    	var img_srl = $('#'+field).val();
    	$('img#'+view).attr('src',img_srl);
    	// $('#modal-file .modal-body').html('');
    });
});	


remove.on("click", function (e) {
    e.preventDefault();
    var field = $(this).data("field");
    var view = $(this).data("view");
    $('img#'+view).attr('src',mo_img);
    $('#'+field).val(mo_img);
    // $('#modal-file .modal-body').load('');
});

$('input#name').keyup(function(event) {
/* Act on the event */
var title, slug;

    //Lấy text từ thẻ input title 
    title = $(this).val();

    //Đổi chữ hoa thành chữ thường
    slug = title.toLowerCase();

    //Đổi ký tự có dấu thành không dấu
    slug = slug.replace(/á|à|ả|ạ|ã|ă|ắ|ằ|ẳ|ẵ|ặ|â|ấ|ầ|ẩ|ẫ|ậ/gi, 'a');
    slug = slug.replace(/é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ/gi, 'e');
    slug = slug.replace(/i|í|ì|ỉ|ĩ|ị/gi, 'i');
    slug = slug.replace(/ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ/gi, 'o');
    slug = slug.replace(/ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự/gi, 'u');
    slug = slug.replace(/ý|ỳ|ỷ|ỹ|ỵ/gi, 'y');
    slug = slug.replace(/đ/gi, 'd');
    //Xóa các ký tự đặt biệt
    slug = slug.replace(/\`|\~|\!|\@|\#|\||\$|\%|\^|\&|\*|\(|\)|\+|\=|\,|\.|\/|\?|\>|\<|\'|\"|\:|\;|_/gi, '');
    //Đổi khoảng trắng thành ký tự gạch ngang
    slug = slug.replace(/ /gi, "-");
    //Đổi nhiều ký tự gạch ngang liên tiếp thành 1 ký tự gạch ngang
    //Phòng trường hợp người nhập vào quá nhiều ký tự trắng
    slug = slug.replace(/\-\-\-\-\-/gi, '-');
    slug = slug.replace(/\-\-\-\-/gi, '-');
    slug = slug.replace(/\-\-\-/gi, '-');
    slug = slug.replace(/\-\-/gi, '-');
    //Xóa các ký tự gạch ngang ở đầu và cuối
    slug = '@' + slug + '@';
    slug = slug.replace(/\@\-|\-\@|\@/gi, '');
    //In slug ra textbox có id “slug”
    $('input#slug').val(slug);

});