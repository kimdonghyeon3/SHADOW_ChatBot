```javascript
<script>
    function deleteKeyword() {
        var flows = document.getElementsByClassName("tbody")[0];
    
        if(flows.rows.length > 1){
            flows.deleteRow(-1);
        }
    }
</script>
```