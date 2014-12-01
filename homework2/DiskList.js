
$(document).ready(function () {
    addToView(disks);
    $("#edit_disk").dialog({
        draggable:true,  //是否可以拖动，默认为true
        resizable:false,  //是否可以改变大小，默认为true
        show: "slide",  //显示方式，slide为滑动
        bgiframe: false, //背景是否变灰
        autoOpen: false, //对话框是否为自动打开
        hide:"slide", //隐藏方式，
        width:300,
        height:300,
        modal:true ,   //是否窗口模式，默认false，
        position:"center",
        buttons: {
            "Ok": function(){
                editData();
                $(this).dialog('close');
            },
            "Cancel": function(){
                $(this).dialog('close');
                return false;
            }
        }
    })

});
function addToView(disks) {
    $("#container").empty();
    var diskDivs;
    diskDivs = _.map(disks, function (disk) {
        return diskDiv(disk);

    });
    _.each(diskDivs, function (diskDiv) {
        $("#container").append(diskDiv);
    })
}

function diskDiv(disk) {
    var n = disks.indexOf(disk)
    return $("<div>")
        .attr("class", "disk1")
        .attr("id",n)
        .append(diskTitle(disk.name))
        .append(diskImg(disk.img))
        .append(diskDesc(disk.desc))
        .append('<button onclick=deletedisk(this)>删除</button>')
        .append( editdisk())
}
function diskTitle(title) {
    return $("<h3>").html(title)
}
function diskImg(img) {
    return $("<img>").attr("src", img);
}
function diskDesc(desc) {
    return $("<p>").html(desc);
}
function add() {
    var newProduct = {
        name: $("#productName").val(),
        img: $("#productImg").val(),
        desc: $("#productDesc").val()
    };
    disks.push(newProduct);
    addToView(disks);
}
function search() {
    var keyWord = $("#search").val();
    var searchResult = _.filter(disks, function (disk) {
        return disk.name.indexOf(keyWord) != -1;
    });
    console.log(searchResult[0]);
    addToView(searchResult);
}
function deletedisk(obj){
    var n = obj.parentElement.id;
    disks= _.without(disks,disks[n])
    addToView(disks)
}
function editdisk(){
    return $("<input>")
        .attr("type","button")
        .attr("class","edit_btn")
        .attr("value","编辑")
        .attr("onclick",'edit_dialog(this)')
}
function edit_dialog(obj){
    var n = obj.parentElement.id;
    $("#edit_disk").dialog("open");
    $("#edit_name").val(disks[n].name)
//    console.log(disks[n].name)
    $("#edit_img").val(disks[n].img)
    $("#edit_desc").val(disks[n].desc)
    $("#edit_disk").attr("data",n)
}
function editData(){
    var newProduct={
        name:$("#edit_name").val(),
        img:$("#edit_img").val(),
        desc:$("#edit_desc").val()
    };
   var n = $("#edit_disk").attr("data")
   disks[n] = newProduct;
    console.log(disks[n])
    addToView(disks)
}