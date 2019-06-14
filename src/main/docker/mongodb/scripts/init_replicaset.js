var status = rs.status();
if (status.errmsg === 'no replset config has been received') {
    rs.initiate();
}
for (var i = 1; i <= param; i++) {
    if (i!==1)
        rs.add(folder+"_miappjhipster-mongodb-node_" + i + ":28018");
}
cfg = rs.conf();
cfg.members[0].host = folder+"_miappjhipster-mongodb-node_1:28018";
rs.reconfig(cfg);
