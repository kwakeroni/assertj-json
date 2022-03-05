echo Using snapshot repo: $(echo $SNAPSHOT_REPO | md5sum)
echo Using  release repo: $(echo $RELEASE_REPO | md5sum)
echo Using   deploy user: $(echo $OSSRH_USER | md5sum)
echo Using     deploy pw: $(echo $OSSRH_PASSWORD | md5sum)

./mvnw --batch-mode --update-snapshots \
  $GOAL -Pcomplete \
  -DaltSnapshotDeploymentRepository="$SNAPSHOT_REPO" \
  -DaltReleaseDeploymentRepository="$RELEASE_REPO"